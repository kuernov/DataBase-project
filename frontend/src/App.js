
import './App.css';
import Navbar from './Components/Navbar/Navbar';
import {BrowserRouter,Routes,Route} from 'react-router-dom';
import ShopCategory from './Pages/ShopCategory';
import LoginSignup from './Pages/LoginSignup';
import Login from './Pages/Login';
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
        <Route path = '/Obudowy' element={<ShopCategory category="Obudowy"/>}/>
        <Route path = '/KartyGraficzne' element={<ShopCategory category="Kartygraficzne"/>}/>
        <Route path = '/Procesory' element={<ShopCategory category="Procesory"/>}/>
        <Route path = '/PłytyGłówne' element={<ShopCategory category="Plytyglowne"/>}/>
        <Route path = '/Zasilacze' element={<ShopCategory category="Zasilacze"/>}/>
        <Route path= "/product" element={<Product/>}>
          <Route path= ':productId' element={<Product/>}/>
        </Route>
        <Route path = '/Koszyk' element={<Cart/>}/>
        <Route path = '/Login' element={<LoginSignup/>}/>
        <Route path = '/logowanie' element={<Login/>}/>
      </Routes>
      <Footer/>
      </BrowserRouter>
    </div>
  );
}

export default App;