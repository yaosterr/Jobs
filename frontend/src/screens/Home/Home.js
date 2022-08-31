import axios from 'axios';
import React, { useState, useEffect, useContext } from 'react';
import { Form, Button, Modal, FormControl, } from "react-bootstrap";
import { Accordion } from "react-bootstrap";
import { useNavigate } from 'react-router-dom';
import { UserContext } from '../../App';
import Header from '../../components/Header';
import SockJS from 'sockjs-client';
import { over } from 'stompjs';

var stompClient = null;
export default function Home() {
    const { user } = useContext(UserContext)
    const [listings, setListings] = useState([])
    const [title, setTitle] = useState("")
    const [description, setDescription] = useState("")
    const [company, setCompany] = useState("")
    const [location, setLocation] = useState("")
    const [message, setMessage] = useState("")
    const [search, setSearch] = useState("")
    const [show, setShow] = useState(false);
    const navigate = useNavigate();

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    useEffect(() => {
        getListings()
        connect()
    },[]);

    const connect = () => {
        let Sock = new SockJS('http://localhost:8080/ws');
        stompClient = over(Sock);
        stompClient.connect({}, onConnected, (err) => { console.log(err) });
    }

    const onConnected = () => {
        stompClient.subscribe('/listingSocket/public', getListings);
    }

    const incrementCount = (listingId) => {
        if (stompClient) {
            stompClient.send("/app/application", {}, JSON.stringify(listingId));
        }
    }
    //example api call
    const getListings = async () => {
        const response = await axios.get("http://localhost:8080/listings/getAll")
        setListings(response.data)

    }

    const createListing = async () => {
        if (!(title && description && company && location)) {
            return
        }
        await axios.post("http://localhost:8080/listings/create",
            {
                title: title,
                description: description,
                company: company,
                location: location,
                recruiterId: user.id,
                recruiterName: user.name,
                applicantCount:0
            })
        window.location.reload(false);
    }

    const apply = async (listingId,recruiterId, recruiterName) => {
        await axios.post("http://localhost:8080/chats/create",
            {
                applicantId: user.id,
                recruiterId: recruiterId,
                applicantName: user.name,
                recruiterName: recruiterName,
                initialMessage: message
            }
        )
        incrementCount(listingId)
        navigate("/inbox")
    }

    const deleteListing = async(listingId)=>{
        await axios.delete(`http://localhost:8080/listings/delete/${listingId}`)
        window.location.reload(false);
    }

    return (
        <div>
            <Header />
            <Form className="mx-5 my-3 d-flex align-items-center">
                <FormControl
                    type="search"
                    placeholder="Search for a role"
                    className="pr-2"
                    value={search}
                    onChange={e => setSearch(e.target.value)}
                />
                {user &&
                    <Button className="ms-3" style={{ width: "200px" }} variant="outline-primary" onClick={handleShow}>Create listing</Button>
                }
            </Form>


            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Create a listing</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group className="mb-3">
                            <Form.Label>Title of role*</Form.Label>
                            <Form.Control type="text" value={title}
                                onChange={(e) => setTitle(e.target.value)} />
                        </Form.Group>

                        <Form.Group className="mb-3">
                            <Form.Label>Company*</Form.Label>
                            <Form.Control type="text" value={company}
                                onChange={(e) => setCompany(e.target.value)} />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Location*</Form.Label>
                            <Form.Control type="text" onChange={(e) => setLocation(e.target.value)} />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Description* (2000 char limit)</Form.Label>
                            <Form.Control as="textarea" rows={3} value={description}
                                onChange={(e) => setDescription(e.target.value)} />
                        </Form.Group>
                        <p>All fields are required</p>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={createListing}>
                        Create
                    </Button>
                </Modal.Footer>
            </Modal>
            <Accordion className="mx-5">
                {listings && listings.filter((listing) => listing.title.toLowerCase().includes(search.toLowerCase()) ||
                    listing.company.toLowerCase().includes(search.toLowerCase()))
                    .map((listing) => {
                        return (
                            <Accordion.Item key={listing.id} eventKey={listing.id}>
                                <Accordion.Header>{listing.title} at {listing.company}</Accordion.Header>
                                <Accordion.Body>
                                    <p>Total applicants: {listing.applicantCount}</p>
                                    <p>{listing.description}</p>
                                    {user ? user.id===listing.recruiterId ? <Button onClick={()=>deleteListing(listing.id)}>Delete listing</Button>:
                                    <div>
                                        <Form >
                                            <Form.Label as={"b"}>Send a message to the recruiter to apply</Form.Label>
                                            <FormControl
                                                placeholder="Hi! My name is __ and here's a little about me and what I'm looking for..."
                                                as="textarea" rows={3}
                                                value={message} onChange={(e) => setMessage(e.target.value)}
                                            />
                                            <Button className="mt-3" onClick={() => apply(listing.id,listing.recruiterId, listing.recruiterName)}>Apply</Button>
                                        </Form>
                                    </div> : <b>Login to apply</b>}
                                </Accordion.Body>
                            </Accordion.Item>
                        )
                    })}
            </Accordion>
        </div>
    );
}