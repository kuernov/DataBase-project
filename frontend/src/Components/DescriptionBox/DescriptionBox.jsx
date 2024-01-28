import React from 'react'
import './DescriptionBox.css'

const DescriptionBox = () => {
  return (
    <div className='descriptionbox'>
        <div className="descriptionbox-navigator">
            <div className="descriptionbox-nav-box">
                Opis
            </div>
            <div className="descriptionbox-nav-box fade">
                Opinie (122)
            </div>
        </div>
        <div className="descriptionbox-description">
            <p> Procesor AMD Ryzen 7 7800X3D to odpowiedź firmy AMD na rosnące wymagania najnowszych gier komputerowych. Dzięki zastosowaniu autorskiej technologii AMD 3D V-Cache oferuje jeszcze wyższą moc obliczeniową i jeszcze wyższą wydajność, by zwiększyć szansę na błyskawiczną reakcję na wydarzenia w ulubionej grze.
            </p>
            <p>Jednym z najistotniejszych parametrów procesora jest rozmiar pamięci podręcznej. To właśnie on może mieć decydujący wpływ na to, jak szybko procesor wykonuje obliczenia. W przypadku AMD Ryzen 7 7800X3D zastosowano technologię AMD 3D V-Cache, która pozwala na znaczące zwiększenie rozmiaru pamięci trzeciego poziomu (L3). Między innymi dzięki temu AMD Ryzen 7 7800X3D to jeden z najlepszych wyborów wśród procesorów dedykowanych graczom.
            </p>
        </div>
    </div>
  )
}

export default DescriptionBox