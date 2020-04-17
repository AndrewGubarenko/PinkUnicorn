import React from 'react';
import NavigationBar from './../components/NavigationBar';
import {createStore} from 'redux';
import allReducers from './../reducers/allReducers.js'

const store = createStore(allReducers);

class NavigationBarContainer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      searchId: "search__header__off",
      fieldData: "",
      headerCategoriesSmallDisplay: "none"
    }
  }

  onChangeFieldData = (event) => {
    this.setState({fieldData: event.target.value});
  }

  onClickSearch = () => {
    if(this.state.searchId === "search__header__off") {
      this.setState({searchId: "search__header__on"});
    } else {
      this.setState({searchId: "search__header__off"});
    }
  }

  onClickBurger = () => {
    if(this.state.headerCategoriesSmallDisplay === "none") {
      this.setState({headerCategoriesSmallDisplay: "block"});
    } else {
      this.setState({headerCategoriesSmallDisplay: "none"});
    }
  }

  render() {
    return(
      <NavigationBar
        searchId={this.state.searchId}
        headerCategoriesSmallDisplay={this.state.headerCategoriesSmallDisplay}
        headerDropMargin={this.state.headerDropMargin}
        onChangeFieldData={this.onChangeFieldData}
        onClickSearch={this.onClickSearch}
        onClickBurger={this.onClickBurger}
        />
    );
  }
}

export default NavigationBarContainer;
