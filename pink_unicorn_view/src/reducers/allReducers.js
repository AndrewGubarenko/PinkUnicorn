import {combineReducers} from 'redux';
import headerReducer from './headerReducer.js';
import carouselProductReducer from './carouselProductReducer.js';
import carouselArticleReducer from './carouselArticleReducer.js';
import carouselSaleReducer from './carouselSaleReducer.js';

const allReducers = combineReducers ({
  categories: headerReducer,
  products: carouselProductReducer,
  articles: carouselArticleReducer,
  sales: carouselSaleReducer
});

export default allReducers;
