import React from 'react';
import {Link} from 'react-router-dom';
import {connect} from 'react-redux';
import Carousel, {consts} from 'react-elastic-carousel';

class CarouselSaleContainer extends React.Component {
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

  showSaleList = () => {
    return this.props.sales.map((sale) => {
      return(
        <div className="product__item" key={sale.id}>
          <div>
            <div className="sale__sprite__number">-{sale.saleSize}%</div>
            <svg className="sale__sprite">
              <use xlinkHref="#sale" />
            </svg>
          </div>
          <img className="product__image" src={sale.image} alt="" />
          <div className="product__name">{sale.name}</div>
          <Link className="button button--rose product__button" to="#">купить</Link>
        </div>
      );
    });
  }

  render() {
    return(
      <Carousel ref={ref => (this.carousel2 = ref)}
                breakPoints={this.breakPoints}
                renderArrow={this.myArrow}
                pagination={false}
                onNextStart={(currentItem, nextItem) => {
                                if(currentItem.index === nextItem.index ){
                                  this.carousel2.goTo(0)
                                }
                              }
                            }
                onPrevStart={(currentItem, nextItem) => {
                                if(currentItem.index === nextItem.index ){
                                  this.carousel2.goTo(10)
                                }
                              }
                            }>
        {this.showSaleList()}
      </Carousel>

    );
  }
}

const mapStateToProps = (state) => {
  return{
    sales: state.sales
  };
}

export default connect(mapStateToProps)(CarouselSaleContainer);
