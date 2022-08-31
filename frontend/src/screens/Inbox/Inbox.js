import axios from 'axios'
import React, { useContext, useEffect, useState } from 'react'
import { Button, Col, Form, FormControl, ListGroup, Row, Tab } from 'react-bootstrap'
import { UserContext } from '../../App'
import Header from '../../components/Header'
import SockJS from 'sockjs-client';
import { over } from 'stompjs';

var stompClient = null;
export function Inbox() {

    const { user } = useContext(UserContext)
    const [chats, setChats] = useState([])
    const [message, setMessage] = useState("")
    const [messages, setMessages] = useState([])



    useEffect(() => {
        getChats()
        connect()
    }, []);

    const connect = () => {
        let Sock = new SockJS('http://localhost:8080/ws');
        stompClient = over(Sock);
        stompClient.connect({}, onConnected, (err) => { console.log(err) });
    }

    const onConnected = () => {
        stompClient.subscribe('/user/' + user.id + '/private', getChats);
    }

    const sendPrivateMessage = (e, recruiter, applicant, chatId) => {
        e.preventDefault()
        if (stompClient) {
            let chatMessage = {
                sender: recruiter === parseInt(user.id) ? recruiter : applicant,
                receiver: recruiter === parseInt(user.id) ? applicant : recruiter,
                body: message,
                chatId: chatId
            };
            stompClient.send("/app/private-message", {}, JSON.stringify(chatMessage));
            setMessages(oldarr => [...oldarr, chatMessage])
            setMessage("")
        }
    }

    //example api call
    const getChats = async () => {
        const response = await axios.get(`http://localhost:8080/chats/getChats/${user.id}`)
        setChats(response.data)
        let temp = []
        response.data.forEach((chat) => {
            temp.push(axios.get(`http://localhost:8080/messages/getMessages/${chat.id}`))
        })
        const messageData = await Promise.all(temp)
        let temp2 = []
        messageData.forEach((chat) => {
            chat.data.forEach((message) => {
                temp2.push(message)
            })
        })
        setMessages(temp2)
    }

    return (
        <>
            <Header />
            <Tab.Container id="list-group-tabs-example" defaultActiveKey="#0">
                <Row className="mx-5">
                    <Col sm={4}>
                        <ListGroup>
                            {chats.map((chat, i) => {
                                return (
                                    <ListGroup.Item key={chat.id} action href={"#" + i}>
                                        {chat.recruiterName + " and " + chat.applicantName}
                                    </ListGroup.Item>
                                )
                            })}
                        </ListGroup>
                    </Col>
                    <Col sm={8}>
                        <Tab.Content>
                            {chats.map((chat, i) => {
                                return (
                                    <Tab.Pane key={chat.id} eventKey={"#" + i} className="border p-3 rounded">
                                        <div style={{maxHeight:"500px",overflow:"scroll"}}>
                                            <p>{chat.applicantName + ": " + chat.initialMessage}</p>
                                            {messages.filter(message => parseInt(message.chatId) === chat.id).map((message,i) => {
                                                return (
                                                    <p key={i}>{parseInt(message.sender) === chat.applicantId ? chat.applicantName : chat.recruiterName}: {message.body}</p>
                                                )
                                            })}
                                        </div>
                                        <Form className="d-flex" onSubmit={(e) => sendPrivateMessage(e, chat.recruiterId, chat.applicantId, chat.id)}>
                                            <FormControl
                                                type="text"
                                                value={message} onChange={e => setMessage(e.target.value)}
                                            />
                                            <Button variant="outline-primary" className="ms-3" type="submit">Send</Button>

                                        </Form>
                                    </Tab.Pane>
                                )
                            })}
                        </Tab.Content>
                    </Col>
                </Row>
            </Tab.Container>
        </>
    )
}


