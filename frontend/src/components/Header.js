import React, { useContext } from 'react'
import { Container, Nav, Navbar } from 'react-bootstrap'
import { UserContext } from '../App'


export default function Header() {

    const {user,setUser} = useContext(UserContext) 
    return (
        <Navbar bg="white" variant="light">
            <Container>
                <Navbar.Brand href="/"><img style={{width:80}} alt="SalJobs"
            src= "https://i.imgur.com/pvb4AP3.png/"/></Navbar.Brand>
            {user && <p style={{color:"grey"}}>Hi {user.name}!</p>}
                <Nav className="justify-content-end">
                    {user && <Nav.Link href="/inbox">Inbox</Nav.Link>}
                    {user ? <Nav.Link href="/login" onClick={()=>setUser(null)}>Log out</Nav.Link>:<Nav.Link href="/login">Login</Nav.Link>}
                    
                </Nav>
            </Container>
        </Navbar>

    )
}
