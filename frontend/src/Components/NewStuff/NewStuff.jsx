import React, { useState, useEffect } from 'react';
import './NewStuff.css';
import Item from '../Item/Item';

const NewStuff = () => {
    const [newProducts, setNewProducts] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const productPromises = [];

                // Pobieranie 8 produktów od id 5 do 12
                for (let i = 5; i <= 12; i++) {
                    const response = await fetch(`http://localhost:8080/products/${i}`);
                    const product = await response.json();
                    productPromises.push(product);
                }

                const productsData = await Promise.all(productPromises);
                setNewProducts(productsData);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    }, []); // Użycie pustej tablicy dependencies powoduje, że useEffect wykonuje się tylko raz po zamontowaniu komponentu

    return (
        <div className='newstuff'>
            <h1>NOWOŚCI</h1>
            <hr />
            <div className="new">
                {newProducts.map((item, i) => (
                    <Item key={i} id={item.id} name={item.name} image={item.image} price={item.price}  />
                ))}
            </div>
        </div>
    );
}

export default NewStuff;
