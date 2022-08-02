import React, {useState} from "react";
import {Link, Route} from "react-router-dom";
import axios from "axios";
import {KAKAO_AUTH_URL} from "../auth/OAuth";
import DANGN_LOGO from "../images/dangn_logo.png"
import KAKAO_BUTTON from "../images/kakao_login_medium_wide.png"
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
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
            <img
                style={{ width: "10rem" }}
                src={DANGN_LOGO}/>
            <h2>당신 근처의 중고책 마켓</h2>
            <div>중고 서적 거래</div>
            <div>지금 내 대학교를 인증하고 시작해보세요!</div>
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
            <a href={KAKAO_AUTH_URL}>
                <img src={KAKAO_BUTTON}/>
            </a>
        </div>
    )
}
