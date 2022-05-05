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
                <NavBar/>
                <Switch>
                    <Route path="/post-list" component={ItemListPage}/>
                    <Route path="/post-detail" component={ItemDetailPage}/>
                    <Route path="/mypage" component={MyPage}/>
                    <Route path="/login" component={LoginPage}/>
                    <Route path="/oauth2/redirect" component={OAuth2RedirectHandler} />
                    <Route path="/signup" component={SignUpPage}/>
                    <Route path="/mypage" component={MyPage}/>
                    <Redirect path="*" to="/post-list"/>
                </Switch>
            </BrowserRouter>
        </div>
    )
}