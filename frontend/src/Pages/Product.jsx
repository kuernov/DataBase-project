import React, { useContext, useEffect, useState } from 'react';
import { ShopContext } from '../Context/ShopContext'
import { useParams } from 'react-router-dom';
import Breadcrum from '../Components/Breadcrums/Breadcrum';
import ProductDisplay from '../Components/ProductDisplay/ProductDisplay';
import DescriptionBox from '../Components/DescriptionBox/DescriptionBox';
import RelatedProducts from '../Components/RelatedProducts/RelatedProducts';

const Product = () => {
    const { all_product } = useContext(ShopContext);
    const { productId } = useParams();
    const [product, setProduct] = useState(null);

    useEffect(() => {
        // Pobieranie danych z backendu
        fetch(`http://localhost:8080/products/${productId}`)
            .then(response => response.json())
            .then(data => setProduct(data))
            .catch(error => console.error('Błąd podczas pobierania danych z backendu:', error));
    }, [productId]);
  return (
    <div>
      <Breadcrum product= {product}/>
      <ProductDisplay product = {product}/>
      <DescriptionBox/>
      <RelatedProducts/>
    </div>
  )
}

export default Product