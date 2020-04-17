import {combineReducers} from 'redux';
import headerReducer from './headerReducer.js';

const allReducers = combineReducers ({
  categories: headerReducer
});

export default allReducers;
