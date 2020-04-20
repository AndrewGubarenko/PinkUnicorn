import {createStore} from 'redux';
import allReducers from './../reducers/allReducers.js'

const store = createStore(allReducers);

export default store;
