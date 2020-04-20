import React from 'react';
import {Link} from 'react-router-dom';
import {connect} from 'react-redux';
import Carousel, {consts} from 'react-elastic-carousel';

class CarouselArticleContainer extends React.Component {
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

  showArticleList = () => {
    return this.props.articles.map((article) => {
      return(
        <div className="product__item" key={article.id}>
          <img className="product__image" src={article.image} alt="" />
          <div className="product__name">{article.name}</div>
          <Link className="button button--rose product__button" to="#">купить</Link>
        </div>
      );
    });
  }

  render() {
    return(
      <Carousel ref={ref => (this.carousel3 = ref)}
                breakPoints={this.breakPoints}
                renderArrow={this.myArrow}
                pagination={false}
                onNextStart={(currentItem, nextItem) => {
                                if(currentItem.index === nextItem.index ){
                                  this.carousel3.goTo(0)
                                }
                              }
                            }
                onPrevStart={(currentItem, nextItem) => {
                                if(currentItem.index === nextItem.index ){
                                  this.carousel3.goTo(10)
                                }
                              }
                            }>
        {this.showArticleList()}
      </Carousel>

    );
  }
}

const mapStateToProps = (state) => {
  return{
    articles: state.articles
  };
}

export default connect(mapStateToProps)(CarouselArticleContainer);
