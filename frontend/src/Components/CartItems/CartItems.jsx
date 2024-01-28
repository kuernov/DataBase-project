import React, { useContext } from 'react'
import './CartItems.css'
import { ShopContext } from '../../Context/ShopContext.jsx'
import remove_icon from '../Assets/cart_cross_icon.png'

const CartItems = () => {
    const {getTotalCartAmount,all_product,cartItems,removeFromCart} = useContext(ShopContext);
  return (
    <div className='cartitems'>
        <div className="cartitems-format-main">
            <p>Produkty</p>
            <p>Tytuł</p>
            <p>Cena</p>
            <p>Ilość</p>
            <p>Łącznie</p>
            <p>Usuń</p>
        </div>
        <hr />
        {all_product.map((e)=>{
                if(cartItems[e.id]>0)
                {
                    return <div>
                              <div className="cartitems-format cartitems-format-main">
                                 <img src={e.image} alt="" className='carticon-product-icon'/>
                                 <p>{e.name}</p>
                                 <p>{e.new_price}zł</p>
                                 <button className='cartitems-quantity'>{cartItems[e.id]}</button>
                                 <p>{e.new_price*cartItems[e.id]}zł</p>
                                 <img className='cartitems-remove-icon' src={remove_icon} onClick={()=>{removeFromCart(e.id)}} alt="" />
                             </div>
                             <hr />
                           </div>
                }
                return null;
            })}
        <div className="cartitems-down">
            <div className="cartitems-total">
                <h1>Wartość Koszyka</h1>
                <div>
                    <div className="cartitems-total-item">
                        <p>Suma</p>
                        <p>{getTotalCartAmount()} zł</p>
                    </div>
                    <hr />
                    <div className="cartitems-total-item">
                        <p>Koszt Dostawy</p>
                        <p>Darmowa</p>
                    </div>
                    <hr />
                    <div className="cartitems-total-item">
                        <h3>Łącznie</h3>
                        <h3>{getTotalCartAmount()} zł</h3>
                    </div>
                </div>
                <button>PRZEJDŹ DO PODSUMOWANIA</button>
            </div>
            <div className="cartitems-promocode">
                <p>Jeśli posiadasz kod promocyjny, wpisz go tutaj</p>
                <div className="cartitems-promobox">
                    <input type="text" placeholder='kod promocyjny'/>
                    <button>Zatwierdź</button>
                </div>
            </div>
        </div>
    </div>
  )
}

export default CartItems