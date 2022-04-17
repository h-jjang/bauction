import React from "react";
import {Link} from "react-router-dom";

export const NavBar = () => {
    return (
        <>
            //TODO: Link 작동에 문제가 있음
            <Link to="/item-detail">
                <button>Item Detail</button>
            </Link>
            <Link to={"/mypage"}>
                <button>My page</button>
            </Link>
        </>
    );
}