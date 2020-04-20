import React, {Component} from 'react';
import NavigationBarContainer from './containers/NavigationBarContainer';
import MainPageContainer from './containers/MainPageContainer';
import FooterContainer from './containers/FooterContainer';
import {BrowserRouter, Route} from 'react-router-dom';
import './App.css';
import {Provider} from 'react-redux'
import store from './reducers/store'

class App extends Component {

  render() {
    return (
      <Provider store={store}>
        <BrowserRouter>
          <div>
            <Route path="/" component={NavigationBarContainer}/>
            <Route path="/main" component={MainPageContainer}/>
            <Route path="/" component={FooterContainer}/>
          </div>
        </BrowserRouter>
      </Provider>
    );
  }
}

export default App;
