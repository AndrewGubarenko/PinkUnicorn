import React from 'react';
import MainPage from './../components/MainPage';
import CarouselProductContainer from './CarouselProductContainer';
import CarouselSaleContainer from './CarouselSaleContainer';
import CarouselArticleContainer from './CarouselArticleContainer';

class MainPageContainer extends React.Component {

  render() {
    return(
      <MainPage
        breakPoints={this.breakPoints}
        myArrow={this.myArrow}
        carouselProduct={<CarouselProductContainer />}
        carouselSale={<CarouselSaleContainer />}
        carouselArticle={<CarouselArticleContainer />}
        />
    );
  }
}

export default MainPageContainer;
