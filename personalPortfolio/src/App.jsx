import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function NavBar() {

}

function Name() {
  return (
    <div>
        <h1>Evelyn Jiang</h1>
      </div>
  )
}

function Image({ src, alt, description }) {
  return (
    <div className="image-container">
      <img src={src} alt={alt} />
      <input
        type="text"
        className="description-input"
        value={description}
        />
    </div>
  );
}

function Homepage() {
  return (
    <div>
      <Name />
      <Image
        src="https://i.pinimg.com/564x/ee/ab/3b/eeab3b1bd24435f6291855e27e741611.jpg"
        alt="About me"
        description="About me"
      />

      <Image
        src="https://i.pinimg.com/564x/10/77/08/1077080adb48503f7d659c6116a32635.jpg"
        alt="Projects"
        description="Projects"
      />
  </div>  
  );
}


export default function MainPage() {
  return (
    <Homepage />
  )
}

