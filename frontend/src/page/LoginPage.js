import React, {useState} from "react";
import {Link, Route} from "react-router-dom";
import axios from "axios";
import {KAKAO_AUTH_URL} from "../auth/OAuth";
import KAKAO_LOGO from "../images/kakao_login_large_wide.png"
import styles from '../App.css';

export const LoginPage = () => {
    // 샘플 코드입니다.
    const [id, setId] = useState("");
    const [pw, setPw] = useState("");

    const changeId = (e) => {
        console.log(e.target.value);
        setId(e.target.value);
    };

    const changePw = (e) => {
        console.log(e.target.value);
        setPw(e.target.value);
    };

    const loginSubmit = () => {
        axios.post("http://localhost:8080/login", {
            id: id,
            pw: pw,
        }).then(res => {
            console.log(res);
        })
    }


    return (
        <div>
            <h1>Login Page</h1>
            <input type={"text"} placeholder={"ID"} onChange={changeId}/><br/>
            <input type={"password"} placeholder={"PW"} onChange={changePw}/><br/>
            <input type={"button"} onClick={loginSubmit} value={"submit"}/>
            <Route>
                    <Link to={"/signup"}><button>Sign Up</button></Link>
            </Route>
            <a href={KAKAO_AUTH_URL}>
                <img src={KAKAO_LOGO}></img>
            </a>
        </div>
    )
}
