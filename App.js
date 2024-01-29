
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
        <Route path = '/Obudowy' element={<ShopCategory category="obudowy"/>}/>
        <Route path = '/KartyGraficzne' element={<ShopCategory category="kartygraficzne"/>}/>
        <Route path = '/procesory' element={<ShopCategory category="procesory"/>}/>
        <Route path = '/PłytyGłówne' element={<ShopCategory category="plytyglowne"/>}/>
        <Route path = '/Zasilacze' element={<ShopCategory category="zasilacze"/>}/>
        <Route path= "/product" element={<Product/>}>
          <Route path= ':productId' element={<Product/>}/>
        </Route>
        <Route path = '/Koszyk' element={<Cart/>}/>
        <Route path = '/Login' element={<LoginSignup/>}/>
      </Routes>
      <Footer/>
      </BrowserRouter>
    </div>
  );
}

export default App;