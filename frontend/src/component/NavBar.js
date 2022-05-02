import React from "react";
import {Link} from "react-router-dom";

export const NavBar = () => {
    return (
        <>
            <Link to="/post-list">
                <button>HOME</button>
            </Link>
            <Link to="/post-detail">
                <button>Item Detail</button>
            </Link>
            <Link to="/mypage">
                <button>My page</button>
            </Link>
            <Link to="/login">
                <button>Login page</button>
            </Link>
            <Link to="/signup">
                <button>Sign Up page</button>
            </Link>
        </>
    );
}