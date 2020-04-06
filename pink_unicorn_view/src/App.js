import React, {Component} from 'react';
import NavigationBarContainer from './containers/NavigationBarContainer';
import MainPageContainer from './containers/MainPageContainer';
import FooterContainer from './containers/FooterContainer';
import {BrowserRouter, Route} from 'react-router-dom';
import './App.css';

class App extends Component {

  render() {
    return (
      <BrowserRouter>
        <div>
          <Route path="/" component={NavigationBarContainer}/>
          <Route path="/main" component={MainPageContainer}/>
          <Route path="/" component={FooterContainer}/>
        </div>
      </BrowserRouter>
    );
  }
}

export default App;
