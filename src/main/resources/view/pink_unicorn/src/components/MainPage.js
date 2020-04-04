import React from 'react';
import './../css/MainPageStyle.css';
import Logo from "./../statics/images/main_page/main_logo_nobg.png";
import FOR_HIM from "./../statics/images/main_page/for_him.jpg";
import FOR_HER from "./../statics/images/main_page/for_her.jpg";
import FOR_BOTH from "./../statics/images/main_page/for_both.jpg";
import {Link} from 'react-router-dom';

class MainPage extends React.Component {

  render() {
    return(
      <div>
        {/*INTRO*/}
        <div className="intro">
          <div className="mainContainer intro__main">
            <div className="logo">
              <img className="logo__image" src={Logo} alt=""/>
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

        {/*SCROLLS*/}
        <div className="scrolls">
          <div className="scrolls__inner">

          </div>

        </div>
      </div>
    );
  }
}

export default MainPage;
