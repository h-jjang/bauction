import React from "react";
import {Link} from "react-router-dom";
import {KAKAO_AUTH_URL} from "../auth/OAuth";
import HOME from "../images/home.png";
import POSTS from "../images/posts.png";
import LOCATION from "../images/location.png";
import CHAT from "../images/chat.png";
import PROFILE from "../images/profile.png";
import styles from "../page/style/Navbar.css"

export const NavBar = () => {
    return (
        <div className={"menu-logo"}>
            <Link to="/login">
                <img
                    style={{ width: "1.5rem" }}
                    src={HOME}/>
                <div>홈</div>
            </Link>
            <Link to="/post-list">
                <img
                    style={{ width: "1.5rem" }}
                    src={POSTS}/>
                <div>동네생활</div>
            </Link>
            <Link to="/location">
                <img
                    style={{ width: "1.5rem" }}
                    src={LOCATION}/>
                <div>내 근처</div>
            </Link>
            <Link to="/chat">
                <img
                    style={{ width: "1.5rem" }}
                    src={CHAT}/>
                <div>채팅</div>
            </Link>
            <Link to="/profile">
                <img
                    style={{ width: "1.5rem" }}
                    src={PROFILE}/>
                <div>나의 당근</div>
            </Link>
        </div>
    );
}