import React from 'react';
import {Link} from 'react-router-dom';
import {connect} from 'react-redux';
import Carousel, {consts} from 'react-elastic-carousel';

class CarouselProductContainer extends React.Component {
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

  showProductList = () => {
    return this.props.products.map((product) => {
      return(
        <div className="product__item" key={product.id}>
          <img className="product__image" src={product.image} alt="" />
          <div className="product__name">{product.name}</div>
          <Link className="button button--rose product__button" to="#">купить</Link>
        </div>
      );
    });
  }

  render() {
    return(
      <Carousel ref={ref => (this.carousel1 = ref)}
                breakPoints={this.breakPoints}
                renderArrow={this.myArrow}
                pagination={false}
                onNextStart={(currentItem, nextItem) => {
                                if(currentItem.index === nextItem.index ){
                                  this.carousel1.goTo(0)
                                }
                              }
                            }
                onPrevStart={(currentItem, nextItem) => {
                                if(currentItem.index === nextItem.index ){
                                  this.carousel1.goTo(10)
                                }
                              }
                            }>
        {this.showProductList()}
      </Carousel>

    );
  }
}

const mapStateToProps = (state) => {
  return{
    products: state.products
  };
}

export default connect(mapStateToProps)(CarouselProductContainer);
