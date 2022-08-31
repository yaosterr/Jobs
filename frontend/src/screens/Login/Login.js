import React, { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../../App";
import Header from "../../components/Header";
import "./Login.css";
const axios = require('axios');

function Login() {

    const { setUser } = useContext(UserContext)
    const [option, setOption] = useState("signIn")
    const [name, setName] = useState("")
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [error,setError] = useState("")
    const navigate = useNavigate();

    const submit = async (e) => {
        e.preventDefault()
        try {
            const response = await axios.post(`http://localhost:8080/users/${option}`,
                {
                    email: email,
                    name: name,
                    password: password
                }
            )
            console.log(response)
            if (!response.data){
                setError("Something went wrong")
            }
            else if (response.data==="Email not found" || response.data==="Incorrect Password"){
                setError(response.data)
            } else{
                setError("")
                const data = response.data.split("%")
                setUser({
                    id:data[0],
                    name:data[1]
                })
                navigate("/")
            }
        } catch (err) {
            console.log(err)
        }

    }

    return (
        <>
            <Header />
            <div className="login">
                <img
                    src="https://i.imgur.com/pvb4AP3.png/" alt="Saljobs" />
                <form onSubmit={(e) => submit(e)}>
                    {option === "signIn" ? null :
                        <input
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            placeholder="Full name"
                            type="text"
                            required
                        />
                    }

                    <input
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        placeholder="Email"
                        type="email"
                        required
                    />

                    <input
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        placeholder="Password"
                        type="password"
                        required
                    />
                    {error && <p style={{color:"red"}}>{error}</p>}
                    <button type="submit">{option === "signIn" ? "Sign in" :  "Register"}</button>
                </form>

                <p>
                    {option === "signIn" ? "Not yet a member? " : "Already a member? "}
                    <span className="login_register" onClick={() => setOption(option === "signIn" ? "register" : "signIn")}>
                        {option === "signIn" ? "Click here to register" : "Click here to sign in"}
                    </span>
                </p>
            </div>
        </>
    );
}

export default Login;