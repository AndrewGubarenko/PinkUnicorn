import UserService from "./../services/UserService";
import AdminService from "./../services/AdminService";
import { createStore } from 'redux';
import mainReducer from './../store/MainReducer';

let store;

let startUrl;

if(process.env.NODE_ENV === "production") {
	startUrl = "./";
	store = createStore(mainReducer);
}

if(process.env.NODE_ENV === "development") {
	startUrl = "http://localhost:8080/PinkUnicorn_war_exploded/";
	store = createStore(mainReducer,
		window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__());
}

const userService = new UserService(startUrl);
const todoService = new AdminService(startUrl);

export {userService, todoService, store};
