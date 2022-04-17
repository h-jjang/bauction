import React from "react";

import {
    BrowserRouter,
    Switch,
    Route,
    Redirect,
} from "react-router-dom";

import {ItemDetailPage} from "./ItemDetailPage";
import {MyPage} from "./MyPage";
import {ItemListPage} from "./ItemListPage";
import {NavBar} from "../component/NavBar";

export const RouterPage = () => {
    return (
        <div>
            <NavBar/>
            <BrowserRouter>
                <Switch>
                    <Route path="/item-list" component={ItemListPage}/>
                    <Route path="/item-detail" component={ItemDetailPage}/>
                    <Route path="/mypage" component={MyPage}/>
                    <Redirect path="*" to="/item-list"/>
                </Switch>
            </BrowserRouter>
        </div>
    )
}