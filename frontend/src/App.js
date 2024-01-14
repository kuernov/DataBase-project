
import './App.css';
import Navbar from './Components/Navbar/Navbar';
import {BrowserRouter,Routes,Route} from 'react-router-dom';
import ShopCategory from './Pages/ShopCategory';
import LoginSignup from './Pages/LoginSignup';
import Cart from './Pages/Cart';
import Product from './Pages/Product';
import Shop from './Pages/Shop';
import Footer from './Components/Footer/Footer';

function App() {
  return (
      <div>
        <BrowserRouter>
          <Navbar/>
          <Routes>
            <Route path = '/' element={<Shop/>}/>
            <Route path = '/1' element={<ShopCategory category="1"/>}/>
            <Route path = '/2' element={<ShopCategory category="2"/>}/>
            <Route path = '/3' element={<ShopCategory category="3"/>}/>
            <Route path = '/4' element={<ShopCategory category="4"/>}/>
            <Route path= "/product" element={<Product/>}>
              <Route path= 'productId' element={<Product/>}/>
            </Route>
            <Route path = '/cart' element={<Cart/>}/>
            <Route path = '/login' element={<LoginSignup/>}/>
          </Routes>
          <Footer/>
        </BrowserRouter>
      </div>
  );
}

export default App;