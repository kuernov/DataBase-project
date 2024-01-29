import React, { useContext, useEffect, useState } from 'react';
import './CSS/ShopCategory.css';
import { ShopContext } from '../Context/ShopContext';
import dropdown_icon from '../Components/Assets/dropdown_icon.png';
import Item from '../Components/Item/Item';
import {useParams} from "react-router-dom";

const ShopCategory = (props) => {
    const { all_product } = useContext(ShopContext);
    const [products, setProducts] = useState([]);
    const category = props.category

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch(`http://localhost:8080/products/byCategory/${props.category}`);
                const data = await response.json();
                console.log('Category parameter:', category);
                console.log('Received data:', data); // Dodaj ten log
                setProducts(data);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    }, [props.category]);

    return (
        <div className='shop-category'>
            <div className="shopcategory-indexSort">
                <p>
                    <span>Showing 1-12</span> out of {products.length} products
                </p>
                <div className="shopcategory-sort">
                    Sort by <img src={dropdown_icon} alt="" />
                </div>
            </div>
            <div className="shopcategory-products">
                {Array.isArray(products) && products.map((item, i) => (
                    <Item
                        key={i}
                        id={item.id}
                        name={item.name}
                        price={item.price}
                        imageURL={item.imageURL}
                    />
                ))}
            </div>
            <div className="shopcategory-loadmore">
                Pokaż więcej
            </div>
        </div>
    );
};

export default ShopCategory;
