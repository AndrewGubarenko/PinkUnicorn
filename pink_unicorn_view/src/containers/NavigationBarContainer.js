import React from 'react';
import NavigationBar from './../components/NavigationBar'

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
        onChangeFieldData={this.onChangeFieldData}
        onClickSearch={this.onClickSearch}
        onClickBurger={this.onClickBurger}
        />
    );
  }
}

export default NavigationBarContainer;
