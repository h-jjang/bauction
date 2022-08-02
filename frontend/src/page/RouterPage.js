import React from "react";

import {
    BrowserRouter,
    Switch,
    Route,
    Redirect,
} from "react-router-dom";
import OAuth2RedirectHandler from "../auth/OAuth2Redirecthandler";

import {ItemDetailPage} from "./ItemDetailPage";
import {MyPage} from "./MyPage";
import {ItemListPage} from "./ItemListPage";
import {NavBar} from "../component/NavBar";
import {LoginPage} from "./LoginPage";
import {SignUpPage} from "./SignUpPage";

export const RouterPage = () => {
    return (
        <div>
            <BrowserRouter>

                <Switch>
                    <Route path="/login" component={LoginPage}/>
                    <Route path="/post-list" component={ItemListPage}/>
                    <Route path="/location" component={ItemDetailPage}/>
                    <Route path="/chat" component={SignUpPage}/>
                    <Route path="/profile" component={MyPage}/>
                    <Route path="/oauth2/redirect" component={OAuth2RedirectHandler} />
                    {/*<Route path="/mypage" component={MyPage}/>*/}
                    <Redirect path="*" to="/login"/>
                </Switch>
                <br/>
                <NavBar/>
            </BrowserRouter>
        </div>
    )
}