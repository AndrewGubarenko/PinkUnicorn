import React from 'react';
import MainPage from './../components/MainPage'
import {consts} from 'react-elastic-carousel';

class MainPageContainer extends React.Component {

  constructor(props) {
    super(props)
    this.breakPoints = [
      {width: 1, itemsToShow: 1},
      {width: 625, itemsToShow: 2},
      {width: 750, itemsToShow: 3}
    ]
    this.goto = this.goto.bind(this)
  }

  goto = ({ target }) => {
    this.carousel.goTo(Number(target.value))
  }

  myArrow = ({ type, onClick }) => {
    const pointer = type === consts.PREV ? <use xlinkHref="#scroll_left" /> : <use xlinkHref="#scroll_right" />
    return <svg onClick={onClick} className="scroll__button">{pointer}</svg>
  }

  render() {
    return(
      <MainPage
        breakPoints={this.breakPoints}
        myArrow={this.myArrow}
        />
    );
  }
}

export default MainPageContainer;
