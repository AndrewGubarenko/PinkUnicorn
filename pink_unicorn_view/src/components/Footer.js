import React from 'react';
import './../css/FooterStyle.css';
import {Link} from 'react-router-dom';

class Footer extends React.Component {
  render() {
    return(
      <div className="mainContainer footer">

        <svg style={{display:"none"}}>
          <symbol id="eighteen" viewBox="0 0 328.863 328.863">
            <g>
            	<g>
            		<path d="M104.032,220.434V131.15H83.392V108.27h49.121v112.164H104.032z"/>
            	</g>
            	<g>
            		<path d="M239.552,137.23c0,9.76-5.28,18.4-14.08,23.201c12.319,5.119,20,15.84,20,28.32c0,20.16-17.921,32.961-45.921,32.961
            			c-28.001,0-45.921-12.641-45.921-32.48c0-12.801,8.32-23.682,21.28-28.801c-9.44-5.281-15.52-14.24-15.52-24
            			c0-17.922,15.681-29.281,40.001-29.281C224.031,107.15,239.552,118.83,239.552,137.23z M180.51,186.352
            			c0,9.441,6.721,14.721,19.041,14.721c12.32,0,19.2-5.119,19.2-14.721c0-9.279-6.88-14.561-19.2-14.561
            			C187.23,171.791,180.51,177.072,180.51,186.352z M183.391,138.83c0,8.002,5.76,12.48,16.16,12.48c10.4,0,16.16-4.479,16.16-12.48
            			c0-8.318-5.76-12.959-16.16-12.959C189.15,125.871,183.391,130.512,183.391,138.83z"/>
            	</g>
            	<g>
            		<path d="M292.864,120.932c4.735,13.975,7.137,28.592,7.137,43.5c0,74.752-60.816,135.568-135.569,135.568
            			S28.862,239.184,28.862,164.432c0-74.754,60.816-135.568,135.569-135.568c14.91,0,29.527,2.4,43.5,7.137V5.832
            			C193.817,1.963,179.24,0,164.432,0C73.765,0,0.001,73.764,0.001,164.432s73.764,164.432,164.431,164.432
            			S328.862,255.1,328.862,164.432c0-14.807-1.962-29.385-5.831-43.5H292.864z"/>
            	</g>
            	<g>
            		<polygon points="284.659,44.111 284.659,12.582 261.987,12.582 261.987,44.111 230.647,44.111 230.647,66.781 261.987,66.781
            			261.987,98.309 284.659,98.309 284.659,66.781 316.186,66.781 316.186,44.111 		"/>
            	</g>
            </g>
          </symbol>
          <symbol id="instagram" viewBox="0 0 480 480">
            <path d="m240 0c-132.546875 0-240 107.453125-240 240s107.453125 240 240 240 240-107.453125 240-240c-.148438-132.484375-107.515625-239.851562-240-240zm0 464c-123.710938 0-224-100.289062-224-224s100.289062-224 224-224 224 100.289062 224 224c-.140625 123.652344-100.347656 223.859375-224 224zm0 0"/>
            <path d="m344 96h-208c-22.082031.027344-39.972656 17.917969-40 40v208c.027344 22.082031 17.917969 39.972656 40 40h208c22.082031-.027344 39.972656-17.917969 40-40v-208c-.027344-22.082031-17.917969-39.972656-40-40zm24 248c0 13.253906-10.746094 24-24 24h-208c-13.253906 0-24-10.746094-24-24v-208c0-13.253906 10.746094-24 24-24h208c13.253906 0 24 10.746094 24 24zm0 0"/>
            <path d="m240 160c-44.183594 0-80 35.816406-80 80s35.816406 80 80 80 80-35.816406 80-80c-.046875-44.164062-35.835938-79.953125-80-80zm0 144c-35.347656 0-64-28.652344-64-64s28.652344-64 64-64 64 28.652344 64 64c-.039062 35.328125-28.671875 63.960938-64 64zm0 0"/>
            <path d="m328 128c-13.253906 0-24 10.746094-24 24s10.746094 24 24 24 24-10.746094 24-24-10.746094-24-24-24zm0 32c-4.417969 0-8-3.582031-8-8s3.582031-8 8-8 8 3.582031 8 8-3.582031 8-8 8zm0 0"/>
          </symbol>
          <symbol id="viber" viewBox="0 0 52.511 52.511">
            <g>
              <path d="M31.256,0H21.254C10.778,0,2.255,8.521,2.255,18.995v9.01c0,7.8,4.793,14.81,12,17.665v5.841
                c0,0.396,0.233,0.754,0.595,0.914c0.13,0.058,0.268,0.086,0.405,0.086c0.243,0,0.484-0.089,0.671-0.259L21.725,47h9.531
                c10.476,0,18.999-8.521,18.999-18.995v-9.01C50.255,8.521,41.732,0,31.256,0z M48.255,28.005C48.255,37.376,40.63,45,31.256,45
                h-9.917c-0.248,0-0.487,0.092-0.671,0.259l-4.413,3.997v-4.279c0-0.424-0.267-0.802-0.667-0.942
                C8.81,41.638,4.255,35.196,4.255,28.005v-9.01C4.255,9.624,11.881,2,21.254,2h10.002c9.374,0,16.999,7.624,16.999,16.995V28.005z"
                />
              <path d="M39.471,30.493l-6.146-3.992c-0.672-0.437-1.472-0.585-2.255-0.423c-0.784,0.165-1.458,0.628-1.895,1.303l-0.289,0.444
                c-2.66-0.879-5.593-2.002-7.349-7.085l0.727-0.632h0c1.248-1.085,1.379-2.983,0.294-4.233l-4.808-5.531
                c-0.362-0.417-0.994-0.46-1.411-0.099l-3.019,2.624c-2.648,2.302-1.411,5.707-1.004,6.826c0.018,0.05,0.04,0.098,0.066,0.145
                c0.105,0.188,2.612,4.662,6.661,8.786c4.065,4.141,11.404,7.965,11.629,8.076c0.838,0.544,1.781,0.805,2.714,0.805
                c1.638,0,3.244-0.803,4.202-2.275l2.178-3.354C40.066,31.413,39.934,30.794,39.471,30.493z M35.91,34.142
                c-0.901,1.388-2.763,1.782-4.233,0.834c-0.073-0.038-7.364-3.835-11.207-7.75c-3.592-3.659-5.977-7.724-6.302-8.291
                c-0.792-2.221-0.652-3.586,0.464-4.556l2.265-1.968l4.152,4.776c0.369,0.424,0.326,1.044-0.096,1.411l-1.227,1.066
                c-0.299,0.26-0.417,0.671-0.3,1.049c2.092,6.798,6.16,8.133,9.13,9.108l0.433,0.143c0.433,0.146,0.907-0.021,1.155-0.403
                l0.709-1.092c0.146-0.226,0.37-0.379,0.63-0.434c0.261-0.056,0.527-0.004,0.753,0.143l5.308,3.447L35.91,34.142z"/>
              <path d="M28.538,16.247c-0.532-0.153-1.085,0.156-1.236,0.688c-0.151,0.531,0.157,1.084,0.688,1.235
                c1.49,0.424,2.677,1.613,3.097,3.104c0.124,0.44,0.525,0.729,0.962,0.729c0.09,0,0.181-0.012,0.272-0.037
                c0.531-0.15,0.841-0.702,0.691-1.234C32.405,18.578,30.69,16.859,28.538,16.247z"/>
              <path d="M36.148,22.219c0.09,0,0.181-0.012,0.272-0.037c0.532-0.15,0.841-0.703,0.691-1.234c-1.18-4.183-4.509-7.519-8.689-8.709
                c-0.531-0.153-1.084,0.158-1.235,0.689c-0.151,0.531,0.157,1.084,0.688,1.235c3.517,1,6.318,3.809,7.311,7.328
                C35.311,21.931,35.711,22.219,36.148,22.219z"/>
              <path d="M27.991,7.582c-0.532-0.153-1.085,0.156-1.236,0.689c-0.151,0.531,0.157,1.084,0.688,1.235
                c5.959,1.695,10.706,6.453,12.388,12.416c0.124,0.44,0.525,0.729,0.962,0.729c0.09,0,0.181-0.012,0.272-0.037
                c0.531-0.15,0.841-0.703,0.691-1.234C39.887,14.753,34.613,9.467,27.991,7.582z"/>
            </g>
          </symbol>
          <symbol id="telegram" viewBox="0 0 512 512">
            <g>
              <g>
              	<g>
              		<path d="M490.626,153.442c-13.697-31.292-33.236-59.158-58.073-82.819c-3.207-3.055-8.28-2.933-11.335,0.275
              			c-3.054,3.206-2.931,8.28,0.275,11.333c48.024,45.751,74.473,107.464,74.473,173.769c0,132.318-107.648,239.967-239.967,239.967
              			S16.033,388.318,16.033,256S123.682,16.033,256,16.033c48.336,0,94.93,14.306,134.742,41.369
              			c3.661,2.489,8.647,1.538,11.137-2.122c2.489-3.662,1.538-8.648-2.123-11.137C357.274,15.265,307.565,0,256,0
              			C187.62,0,123.333,26.628,74.981,74.981C26.629,123.333,0,187.62,0,256s26.629,132.667,74.981,181.019
              			C123.333,485.372,187.62,512,256,512s132.667-26.628,181.019-74.981C485.371,388.667,512,324.38,512,256
              			C512,220.348,504.808,185.842,490.626,153.442z"/>
              	</g>
              </g>
              <g>
              	<g>
              		<path d="M372.333,108.552l-154.176,71.771c-4.014,1.868-5.753,6.638-3.884,10.652s6.638,5.755,10.65,3.885l95.106-44.274
              			l-46.237,37.431l-106.107,85.896l-58.036-25.392l81.326-37.858c4.014-1.87,5.753-6.638,3.884-10.652
              			c-1.868-4.014-6.639-5.755-10.65-3.885l-87.54,40.752c-4.654,2.166-7.592,6.905-7.474,12.035c0.115,5.02,3.149,9.538,7.748,11.55
              			l64.802,28.35l18.979,113.873c1.168,7.041,10.702,9.046,14.613,3.075l53.836-82.171l101.811,47.512
              			c8.157,3.81,17.864-2.012,18.39-10.966l14.344-243.849c0.015-0.226,0.014-0.458,0.009-0.685
              			C383.567,109.927,377.471,106.176,372.333,108.552z M190.018,360.931l-12.404-74.428l126.369-102.299l-96.718,108.441
              			c-0.87,0.976-1.516,2.204-1.816,3.479L190.018,360.931z M207.049,358.631l11.72-49.228l15.724,7.338L207.049,358.631z
              			 M243.371,303.191l-16.967-7.917l83.469-93.586L297.8,220.116L243.371,303.191z M353.637,354.649l-95.586-44.607l107.897-164.684
              			L353.637,354.649z"/>
              	</g>
              </g>
            </g>
          </symbol>
        </svg>

        <div id="footer__about__wide" className="footer__about">
          <div className="footer__about__half">
            <Link className="footer__about__link" to="#">О нас</Link>
            <Link className="footer__about__link" to="#">Контакты</Link>
            <Link className="footer__about__link" to="#">Ообратная связь</Link>
          </div>
          <div className="footer__about__half">
            <Link className="footer__about__link" to="#">Оплата и доставка</Link>
            <Link className="footer__about__link" to="#">Пользовательское соглашение</Link>
            <Link className="footer__about__link" to="#">Политика конфиденциальности</Link>
          </div>
        </div>
        <div id="footer__about__narrow" className="footer__about">
          <Link className="footer__about__link" to="#">О нас</Link>
          <Link className="footer__about__link" to="#">Контакты</Link>
          <Link className="footer__about__link" to="#">Ообратная связь</Link>
          <Link className="footer__about__link" to="#">Оплата и доставка</Link>
          <Link className="footer__about__link" to="#">Пользовательское соглашение</Link>
          <Link className="footer__about__link" to="#">Политика конфиденциальности</Link>
        </div>
        <div className="footer__social">
          <div className="footer__social__header">Мы в соцсетях:</div>
          <div className="footer__social__icons">
            <a href="https://www.instagram.com/pink_unicorn_store/?igshid=1hkfsow4fkm7k" target="blanc">
              <svg className="footer__social__single__icon">
                <use xlinkHref="#instagram"/>
              </svg>
            </a>
            <a href="viber://chat?number=380934228227" target="blanc">
              <svg className="footer__social__single__icon">
                <use xlinkHref="#viber"/>
              </svg>
            </a>
            <a href="https://telegram.me/OlenaPolinovskaUKOO" target="blanc">
              <svg className="footer__social__single__icon">
                <use xlinkHref="#telegram"/>
              </svg>
            </a>
          </div>
          <div className="footer__eighteen_prohibited">
            <div className="footer__eighteen__rule">
              <svg className="footer__eighteen__icon">
                <use xlinkHref="#eighteen"/>
              </svg>
              Посещение сайта лицам не достигшим 18 лет строго запрещено!</div>
          </div>
        </div>
        <div className="footer__licences">
          <div className="copyright" >Copyright © 2020 All rights reserved</div>
          <div className="ref__to__icon__designer__container">
            <ul className="ref__icon_author__menu">
              <li className="ref__icon_author__list"><div className="ref__to__icon__designer__prefix">Icons made by:</div>
                <ul className="ref__icon_author__drop">
                  <li><div className="footer__single__author"><a className="ref__to__icon__designer" href="https://www.flaticon.com/authors/freepik" title="Freepik" target="blank">Freepik</a> from <a className="ref__to__icon__designer" href="https://www.flaticon.com/" title="Flaticon" target="blank">www.flaticon.com</a></div></li>
                  <li><div className="footer__single__author"><a className="ref__to__icon__designer" href="https://www.flaticon.com/authors/pixel-perfect" title="Pixel perfect" target="blank">Pixel perfect</a> from <a className="ref__to__icon__designer" href="https://www.flaticon.com/" title="Flaticon" target="blank">www.flaticon.com</a></div></li>
                  <li><div className="footer__single__author"><a className="ref__to__icon__designer" href="https://www.flaticon.com/authors/roundicons" title="Roundicons" target="blank">Roundicons</a> from <a className="ref__to__icon__designer" href="https://www.flaticon.com/" title="Flaticon" target="blank">www.flaticon.com</a></div></li>
                  <li><div className="footer__single__author"><a className="ref__to__icon__designer" href="https://www.flaticon.com/authors/payungkead" title="Payungkead" target="blank">Payungkead</a> from <a className="ref__to__icon__designer" href="https://www.flaticon.com/" title="Flaticon" target="blank">www.flaticon.com</a></div></li>
                  <li><div className="footer__single__author"><a className="ref__to__icon__designer" href="https://www.flaticon.com/authors/swifticons" title="Swifticons" target="blank">Swifticons</a> from <a className="ref__to__icon__designer" href="https://www.flaticon.com/" title="Flaticon" target="blank">www.flaticon.com</a></div></li>
                  <li><div className="footer__single__author"><a className="ref__to__icon__designer" href="https://www.flaticon.com/authors/prosymbols" title="Prosymbols" target="blank">Prosymbols</a> from <a className="ref__to__icon__designer" href="https://www.flaticon.com/" title="Flaticon" target="blank">www.flaticon.com</a></div></li>
                </ul>
              </li>
            </ul>
          </div>
        </div>
      </div>
    );
  }
}
export default Footer;
