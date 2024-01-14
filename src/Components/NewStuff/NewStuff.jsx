import React from 'react'
import './NewStuff.css'
import news from '../Assets/new_collections'
import Item from '../Item/Item'

const NewStuff = () => {
  return (
    <div className='newstuff'>
        <h1>NOWOŚCI</h1>
        <hr />
        <div className="new">
            {news.map((item,i)=>{
                return <Item key={i} id={item.id} name={item.name} image={item.image}
                new_price={item.new_price} old_price={item.old_price}/>
            })}
        </div>
    </div>
  )
}

export default NewStuff