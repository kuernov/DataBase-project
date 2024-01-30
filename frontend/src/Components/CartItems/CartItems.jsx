import React, { useContext, useEffect, useState } from 'react';
import './CartItems.css';
import { ShopContext } from '../../Context/ShopContext.jsx';
import remove_icon from '../Assets/cart_cross_icon.png';
import { useNavigate } from 'react-router-dom';

const CartItems = () => {
    const { getTotalCartAmount, all_product, cartItems, removeFromCart } = useContext(ShopContext);
    const [cartData, setCartData] = useState([]);
    const [totalPrice, setTotalPrice] = useState(0);
    const navigate = useNavigate();
    useEffect(() => {
        // Funkcja do pobierania danych z koszyka z serwera
        const fetchCartData = async () => {
            try {
                const token = localStorage.getItem('accessToken'); // Pobierz token z localStorage
                const response = await fetch('http://localhost:8080/cart', {
                    method: 'GET',
                    headers: {
                        'Authorization': `Bearer ${token}`,
                        'Content-Type': 'application/json',
                    },
                    credentials: 'include',
                });

                if (response.ok) {
                    console.log(localStorage.getItem('accessToken'));
                    const data = await response.json();
                    console.log(data)
                    setCartData(data);
                    console.log('cartData:', cartData);

                } else {
                    console.error('Failed to fetch cart data');
                }
            } catch (error) {
                console.error('Error fetching cart data:', error);
            }
        };


        fetchCartData();
    }, []);


    useEffect(() => {
        // Check if cartData has a totalPrice property before updating
        if (cartData && cartData.totalPrice !== undefined) {
            setTotalPrice(cartData.totalPrice.toFixed(2));
        }
    }, [cartData]);

    const handleRemoveItem = async (itemId) => {
        try {
            const token = localStorage.getItem('accessToken');
            const response = await fetch(`http://localhost:8080/cart/delete/${itemId}`, {
                method: 'DELETE',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
                credentials: 'include',
            });

            if (response.ok) {
                // Aktualizacja danych po usunięciu przedmiotu z koszyka
                const updatedCartData = cartData.cartItems.filter(item => item.product.id !== itemId);
                setCartData({ cartItems: updatedCartData });
                navigate();
            } else {
                console.error('Failed to remove item from cart');
            }
        } catch (error) {
            console.error('Error removing item from cart:', error);
        }
    };

    const handleUpdateQuantity = async (itemId, newQuantity) => {
        try {
            const token = localStorage.getItem('accessToken');
            const response = await fetch(`http://localhost:8080/cart/update?id=${itemId}&quantity=${newQuantity}`, {
                method: 'PUT',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
                credentials: 'include',
            });

            if (response.ok) {
                // Aktualizacja danych po zmianie ilości przedmiotu w koszyku
                const updatedCartItems = cartData.cartItems.map(item => {
                    if (item.product.id === itemId) {
                        return { ...item, quantity: newQuantity };
                    } else {
                        return item;
                    }
                });

                // Update cartData with the new cartItems array
                setCartData({ ...cartData, cartItems: updatedCartItems });
                setTotalPrice(cartData.totalPrice.toFixed(2));

            } else {
                console.error('Failed to update quantity');
            }
        } catch (error) {
            console.error('Error updating quantity:', error);
        }
    };

    const handleCheckout = async () => {
        try {
            const token = localStorage.getItem('accessToken');
            const response = await fetch('http://localhost:8080/cart/checkout', {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
                credentials: 'include',
            });

            if (response.ok) {
                // W przypadku sukcesu można dodać dodatkową logikę, np. wyświetlić potwierdzenie zamówienia.
                console.log('Zamówienie zostało złożone pomyślnie!');
                window.location.reload();
            } else {
                console.error('Failed to checkout');
            }
        } catch (error) {
            console.error('Error during checkout:', error);
        }
    };

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
          {Array.isArray(cartData.cartItems) && cartData.cartItems.length > 0 ? (
              cartData.cartItems.map((e) => {
                  return (
                      <div key={e.product.id}>
                          <div className="cartitems-format cartitems-format-main">
                              <img src={process.env.PUBLIC_URL + `/product_${e.product.id}.png`} alt="" className='carticon-product-icon'/>
                              <p>{e.product.name}</p>
                              <p>{e.product.price}zł</p>
                              <input
                                  type="number"
                                  className="cartitems-quantity"
                                  value={e.quantity}
                                  onChange={(event) => {
                                      handleUpdateQuantity(e.product.id, event.target.value)
                                  }}
                              />
                              <p>{(e.product.price * e.quantity).toFixed(2)}zł</p>
                              <img className='cartitems-remove-icon' src={remove_icon} onClick={() => {
                                  handleRemoveItem(e.product.id)
                              }} alt=""/>
                          </div>
                          <hr/>
                      </div>
                  );
              })
          ) : (
              <p>Brak elementów w koszyku</p>
          )}

          <div className="cartitems-down">
              <div className="cartitems-total">
                  <h1>Wartość Koszyka</h1>
                  <div>
                    <div className="cartitems-total-item">
                        <p>Suma</p>
                        <p>{totalPrice} zł</p>
                    </div>
                    <hr />
                    <div className="cartitems-total-item">
                        <p>Koszt Dostawy</p>
                        <p>Darmowa</p>
                    </div>
                    <hr />
                    <div className="cartitems-total-item">
                        <h3>Łącznie</h3>
                        <h3>{totalPrice} zł</h3>
                    </div>
                </div>
                  <button onClick={handleCheckout}>ZAMÓW</button>
              </div>

          </div>
      </div>
  )
}

export default CartItems