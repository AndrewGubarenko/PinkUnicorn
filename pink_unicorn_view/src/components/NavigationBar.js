import React from 'react';
import './../css/NavigationBarStyle.css';

class NavigationBar extends React.Component {
  render() {
    return(
      <header className="header">
        {/*{SPRITES}*/}
        <svg style={{display:"none"}}>
          <symbol id="search" viewBox="0 0 510.527 510.527">
            <g>
            	<g>
            		<path d="M217.975,102.4c-15.36,0-25.6,10.24-25.6,25.6s10.24,25.6,25.6,25.6c35.84,0,64,28.16,64,64c0,15.36,10.24,25.6,25.6,25.6
            			c15.36,0,25.6-10.24,25.6-25.6C333.175,153.6,281.975,102.4,217.975,102.4z"/>
            	</g>
            </g>
            <g>
            	<g>
            		<path d="M494.455,435.2l-107.52-76.8c30.72-38.4,51.2-87.04,51.2-138.24C435.575,97.28,338.295,0,217.975,0
            			S0.375,97.28,0.375,217.6s97.28,217.6,217.6,217.6c53.76,0,99.84-17.92,138.24-51.2l76.8,107.52c15.36,23.04,46.08,25.6,64,5.12
            			C514.935,476.16,514.935,450.56,494.455,435.2z M217.975,384c-92.16,0-166.4-74.24-166.4-166.4s74.24-166.4,166.4-166.4
            			s166.4,74.24,166.4,166.4S310.135,384,217.975,384z"/>
            	</g>
            </g>


          </symbol>
          <symbol id="enter" viewBox="0 0 512 512">
            <path d="m465.535156.320312c-.53125-.042968-.980468-.320312-1.535156-.320312h-229.332031c-35.285157 0-64 28.714844-64 64v21.332031c0 11.777344 9.554687 21.335938 21.332031 21.335938s21.332031-9.558594 21.332031-21.335938v-21.332031c0-11.753906 9.578125-21.332031 21.335938-21.332031h99.390625l-6.507813 2.175781c-17.277343 5.972656-28.882812 22.25-28.882812 40.488281v320h-64c-11.757813 0-21.335938-9.578125-21.335938-21.332031v-42.667969c0-11.773437-9.554687-21.332031-21.332031-21.332031s-21.332031 9.558594-21.332031 21.332031v42.667969c0 35.285156 28.714843 64 64 64h64v21.332031c0 23.53125 19.132812 42.667969 42.664062 42.667969 4.566407 0 8.898438-.660156 13.589844-2.113281l128.171875-42.730469c17.300781-5.972656 28.90625-22.25 28.90625-40.488281v-384c0-24.875-21.441406-44.375-46.464844-42.347657zm0 0"/><path d="m228.414062 198.25-85.332031-85.332031c-6.101562-6.101563-15.273437-7.9375-23.253906-4.628907-7.957031 3.304688-13.160156 11.09375-13.160156 19.710938v64h-85.335938c-11.773437 0-21.332031 9.558594-21.332031 21.332031 0 11.777344 9.558594 21.335938 21.332031 21.335938h85.335938v64c0 8.617187 5.203125 16.402343 13.160156 19.710937 7.980469 3.304688 17.152344 1.472656 23.253906-4.628906l85.332031-85.335938c8.34375-8.339843 8.34375-21.820312 0-30.164062zm0 0"/>
          </symbol>
          <symbol id="heart" viewBox="0 0 512 512">
            <g>
          		<path d="M375.467,22.164c-60.061,0-99.769,32.796-119.467,54.493c-19.697-21.697-59.406-54.493-119.467-54.493
          			C56.197,22.164,0,96.392,0,183.743c0,63.878,32.211,188.323,247.931,304.064c5.04,2.705,11.101,2.703,16.138,0
          			C479.79,372.066,512,247.622,512,183.743C512,98.373,457.419,22.164,375.467,22.164z"/>
          	</g>
          </symbol>
          <symbol id="cart" viewBox="0 0 512 512">
            <g>
          		<path d="M447.988,139.696c-0.156-2.084-1.9-3.696-3.988-3.696h-72v-20C372,52.036,319.96,0,256,0S140,52.036,140,116v20H68
          			c-2.088,0-3.832,1.612-3.988,3.696l-28,368c-0.084,1.108,0.296,2.204,1.056,3.02C37.824,511.536,38.888,512,40,512h432
          			c1.112,0,2.176-0.464,2.932-1.28c0.756-0.816,1.14-1.912,1.056-3.02L447.988,139.696z M172,116c0-46.316,37.68-84,84-84
          			s84,37.684,84,84v20H172V116z M156,248c-22.06,0-40-17.944-40-40c0-15.964,8-30.348,24-36.66V208c0,8.824,7.18,16,16,16
          			s16-7.176,16-16v-36.636c16,6.312,24,20.804,24,36.636C196,230.056,178.06,248,156,248z M356,248c-22.06,0-40-17.944-40-40
          			c0-15.964,8-30.348,24-36.66V208c0,8.824,7.18,16,16,16s16-7.176,16-16v-36.636c16,6.312,24,20.804,24,36.636
          			C396,230.056,378.06,248,356,248z"/>
          	</g>
          </symbol>
          <symbol id="user" viewBox="0 0 409.165 409.164">
            	<g>
            		<path d="M204.583,216.671c50.664,0,91.74-48.075,91.74-107.378c0-82.237-41.074-107.377-91.74-107.377
            			c-50.668,0-91.74,25.14-91.74,107.377C112.844,168.596,153.916,216.671,204.583,216.671z"/>
            		<path d="M407.164,374.717L360.88,270.454c-2.117-4.771-5.836-8.728-10.465-11.138l-71.83-37.392
            			c-1.584-0.823-3.502-0.663-4.926,0.415c-20.316,15.366-44.203,23.488-69.076,23.488c-24.877,0-48.762-8.122-69.078-23.488
            			c-1.428-1.078-3.346-1.238-4.93-0.415L58.75,259.316c-4.631,2.41-8.346,6.365-10.465,11.138L2.001,374.717
            			c-3.191,7.188-2.537,15.412,1.75,22.005c4.285,6.592,11.537,10.526,19.4,10.526h362.861c7.863,0,15.117-3.936,19.402-10.527
            			C409.699,390.129,410.355,381.902,407.164,374.717z"/>
            	</g>
          </symbol>
        </svg>

        <div className="mainContainer">
          <div className="header__inner">

            {/*{ICONS}*/}
            <div className="header__icons">
                <svg className="icon">
                  <use xlinkHref="#cart"/>
                </svg>
                <svg className="icon">
                  <use xlinkHref="#heart"/>
                </svg>
                <svg id="auth__user__icon" className="icon">
                  <use xlinkHref="#user"/>
                </svg>
                <svg id="unauth__user__icon" className="icon">
                  <use xlinkHref="#enter"/>
                </svg>
                <svg className="icon" onClick={this.props.onClickSearch}>
                  <use xlinkHref="#search"/>
                </svg>
                <input id={this.props.searchId} className="search" name="search" type="text" placeholder="поиск" onChange={this.props.onChangeFieldData} value={this.props.fieldData}/>
            </div>

            {/*{CATEGORIES}*/}
            <nav id="header__categories__full" className="header__categories">
              <ul className="header__menu">
                {this.props.categories}
              </ul>
            </nav>

            {/*burger*/}
            <div className="burger">
              <span className="burger__item"  onClick={this.props.onClickBurger}>menu</span>

            </div>
            <nav id="header__categories__small" className="header__categories" style={{display: this.props.headerCategoriesSmallDisplay}}>
              <ul className="header__menu">
                {this.props.categories}
              </ul>
            </nav>
          </div>
        </div>
      </header>
    );
  }
}

export default NavigationBar;
