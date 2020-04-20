import React from 'react';
import {Link} from 'react-router-dom';
import {connect} from 'react-redux';

let i = 0;

class CategoriesContainer extends React.Component {

  showCatList = () => {
    return this.props.categories.map((category) => {
      return(
        <li className="header__menu__list" key={category.id}>
          <Link className="nav__link" to="#">{category.name}</Link>
          <ul className="header__menu__drop">
            {category.subcategories.map((subcat) => {
              return(
                <li key={i++}><Link className="nav__link nav__subcat" to="#">{subcat}</Link></li>
              );
            })}
          </ul>
        </li>
      );
    });
  }

  render() {
    return(
      this.showCatList()
    );
  }
}

const mapStateToProps = (state) => {
  return{
    categories: state.categories
  };
}

export default connect(mapStateToProps)(CategoriesContainer);
