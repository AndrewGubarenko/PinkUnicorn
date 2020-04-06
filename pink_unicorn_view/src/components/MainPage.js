import React from 'react';
import './../css/MainPageStyle.css';
import LOGO from "./../statics/images/main_page/main_logo_nobg.png";
import FOR_HIM from "./../statics/images/main_page/for_him.jpg";
import FOR_HER from "./../statics/images/main_page/for_her.jpg";
import FOR_BOTH from "./../statics/images/main_page/for_both.jpg";
import HITS from "./../statics/images/main_page/hits.jpg";
import SALE from "./../statics/images/main_page/sale.jpg";
import ARTICLES from "./../statics/images/main_page/articles.jpg";
import product1 from "./../statics/images/main_page/product1.jpg";
import product2 from "./../statics/images/main_page/product2.jpg";
import product3 from "./../statics/images/main_page/product3.jpg";
import article1 from "./../statics/images/main_page/article1.jpg";
import article2 from "./../statics/images/main_page/article2.png";
import article3 from "./../statics/images/main_page/article3.png";
import delivery from "./../statics/images/main_page/delivery.png";
import returning from "./../statics/images/main_page/return.png";
import send from "./../statics/images/main_page/send.png";
import {Link} from 'react-router-dom';

class MainPage extends React.Component {

  render() {
    return(
      <div>
        {/*{SPRITES}*/}
        <svg style={{display:"none"}}>
          <symbol id="sale" viewBox="0 0 360 360">
            <path d="M360,0v360C161.2,360,0,198.8,0,0H360z"/>
          </symbol>
          <symbol id="scroll_left" viewBox="0 0 50 50">
            <polyline class="st0" points="34.7,5.4 15.2,25 34.8,44.6 "/>
          </symbol>
          <symbol id="scroll_right" viewBox="0 0 50 50">
            <polyline class="st0" points="15.3,5.4 34.8,25 15.2,44.6 "/>
          </symbol>
        </svg>
        {/*INTRO*/}
        <div className="intro">
          <div className="mainContainer intro__main">
            <div className="logo">
              <img className="logo__image" src={LOGO} alt=""/>
            </div>
            <div className="intro__title">
              <div className="intro__title__inner">
                <h1 className="intro__title__header1">Just be</h1>
                <h1 className="intro__title__header1">a</h1>
                <h1 className="intro__title__header1">unicorn</h1>
                <Link className="button button--rose" href="#">подробнее</Link>
              </div>
            </div>
          </div>
        </div>

        {/*POPULAR_CATEGORIES*/}
        <div className="pop_cats">
          <div className="mainContainer pop_cats__inner">

            <div className="single_cat">
              <img className="single_cat_image" src={FOR_HER} alt=""/>
              <h4 className="single_cat_name">для нее</h4>
              <Link className="single_cat_content">для нее</Link>
            </div>

            <div className="single_cat">
              <img className="single_cat_image" src={FOR_HIM} alt=""/>
              <h4 className="single_cat_name">для него</h4>
              <Link className="single_cat_content">для него</Link>
            </div>

            <div className="single_cat">
              <img className="single_cat_image" src={FOR_BOTH} alt=""/>
              <h4 className="single_cat_name">для двоих</h4>
              <Link className="single_cat_content">для двоих</Link>
            </div>

          </div>
        </div>

        {/*SCROLL_BLOCKS*/}
        <div className="scroll_blocks">

          {/*HITS*/}
          <div className="single__scroll__block" style={{"--container_color": "#fff"}}>
            {/*ARROW_BLOCK*/}
            <div className="scroll__arrow__pointer" style={{"--arrow_color": "#e9e9e9"}}>
              <div className="scroll__pointer__box">
                <div className="scroll__pointer__header">хиты продаж</div>
                <img className="scroll__pointer__image" src={HITS} alt="" />
              </div>
            </div>
            {/*SCROLLING_BLOCK*/}
            <div className="carousel__block">
              <svg className="scroll__button">
                <use xlinkHref="#scroll_left" />
              </svg>
              <div className="product__item">
                <img className="product__image" src={product1} alt="" />
                <div className="product__name">Маструбатор Svacom</div>
                <Link className="button button--rose product__button" href="#">купить</Link>
              </div>

              <div className="product__item">
                <img className="product__image" src={product2} alt="" />
                <div className="product__name">Лубрикант на водной основе JO</div>
                <Link className="button button--rose product__button" href="#">купить</Link>
              </div>

              <div className="product__item">
                <img className="product__image" src={product3} alt="" />
                <div className="product__name">Маструбатор Blow You</div>
                <Link className="button button--rose product__button" href="#">купить</Link>
              </div>
              <svg className="scroll__button">
                <use xlinkHref="#scroll_right" />
              </svg>
            </div>

          </div>

          {/*SALE*/}
          <div className="single__scroll__block">
            {/*ARROW_BLOCK*/}
            <div className="scroll__arrow__pointer" style={{"--arrow_color": "#ffc2c7"}}>
              <div className="scroll__pointer__box">
                <div className="scroll__pointer__header">акции</div>
                <img className="scroll__pointer__image" src={SALE} alt="" />
              </div>
            </div>
            {/*SCROLLING_BLOCK*/}
            <div className="carousel__block">
              <svg className="scroll__button">
                <use xlinkHref="#scroll_left" />
              </svg>
              <div className="product__item">
                <div>
                  <div className="sale__sprite__number">-5%</div>
                  <svg className="sale__sprite">
                    <use xlinkHref="#sale" />
                  </svg>
                </div>
                <img className="product__image" src={product1} alt="" />
                <div className="product__name">Маструбатор Svacom</div>
                <Link className="button button--rose product__button" href="#">купить</Link>
              </div>

              <div className="product__item">
                <div>
                  <div className="sale__sprite__number">-15%</div>
                  <svg className="sale__sprite">
                    <use xlinkHref="#sale" />
                  </svg>
                </div>
                <img className="product__image" src={product2} alt="" />
                <div className="product__name">Лубрикант на водной основе JO</div>
                <Link className="button button--rose product__button" href="#">купить</Link>
              </div>

              <div className="product__item">
                <div>
                  <div className="sale__sprite__number">-5%</div>
                  <svg className="sale__sprite">
                    <use xlinkHref="#sale" />
                  </svg>
                </div>
                <img className="product__image" src={product3} alt="" />
                <div className="product__name">Маструбатор Blow You</div>
                <Link className="button button--rose product__button" href="#">купить</Link>
              </div>
              <svg className="scroll__button">
                <use xlinkHref="#scroll_right" />
              </svg>
            </div>
          </div>

          {/*ARTICLES*/}
          <div className="single__scroll__block" style={{"--container_color": "#C6F7FD"}}>
            {/*ARROW_BLOCK*/}
            <div className="scroll__arrow__pointer" style={{"--arrow_color": "#fae8e6"}}>
              <div className="scroll__pointer__box">
                <div className="scroll__pointer__header">статьи</div>
                <img className="scroll__pointer__image" src={ARTICLES} alt="" />
              </div>
            </div>
            {/*SCROLLING_BLOCK*/}
            <div className="carousel__block">
              <svg className="scroll__button">
                <use xlinkHref="#scroll_left" />
              </svg>
              <div className="product__item">
                <img className="product__image" src={article1} alt="" />
                <div className="product__name">Страсть или ...?</div>
                <Link className="button button--rose product__button" href="#">купить</Link>
              </div>

              <div className="product__item">
                <img className="product__image" src={article2} alt="" />
                <div className="product__name">Оральные ласки</div>
                <Link className="button button--rose product__button" href="#">купить</Link>
              </div>

              <div className="product__item">
                <img className="product__image" src={article3} alt="" />
                <div className="product__name">Как выбрать лубрикант?</div>
                <Link className="button button--rose product__button" href="#">купить</Link>
              </div>
              <svg className="scroll__button">
                <use xlinkHref="#scroll_right" />
              </svg>
            </div>
          </div>

          {/*INFO_BLOCK*/}
          <div className="info__block">
            <div className="mainContainer info__main__container">
              <div className="info__inner">
                <img className="info__image" src={delivery} alt=""/>
                <div className="info__text">
                  Бесплатная доставка от 700грн
                </div>
              </div>
              <div className="info__inner">
                <img className="info__image" src={returning} alt=""/>
                <div className="info__text">
                  Товары эротического характера не подлежат возврату или замене
                </div>
              </div>
              <div className="info__inner">
                <img className="info__image" src={send} alt=""/>
                <div className="info__text">
                  Отправка товара в течени 24ч
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>
    );
  }
}

export default MainPage;
