import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function Name() {
  return (
    <div>
      <h1>Evelyn Jiang</h1>
    </div>
  );
}

function Image({ src, alt, description, link }) {
  return (
    <div className="image-container">
      <a href={link} target="_blank" rel="noopener noreferrer">
        <img src={src} alt={alt} />
        <input
          type="text"
          className="description-input"
          value={description}
          readOnly
        />
      </a>
    </div>
  );
}

function Homepage() {
  const images = [
    {
      src: 'https://i.pinimg.com/564x/ee/ab/3b/eeab3b1bd24435f6291855e27e741611.jpg',
      alt: 'About me',
      description: 'About me',
      link: 'https://aces.umd.edu/evelyn-jiang',
    },
    {
      src: 'https://i.pinimg.com/564x/10/77/08/1077080adb48503f7d659c6116a32635.jpg',
      alt: 'Projects',
      description: 'Projects',
      link: 'https://github.com/eve1ynjiang?tab=repositories',
    },
  ];

  const [dark, setDark] = useState(false)

  function handleClick() {
    setDark(!dark)
  }

  return (
    <div className="page-container">
      <div className={dark ? 'content-container dark-mode' : 'content-container'}>
        <Name />
        {images.map((image, index) => (
          <Image
            key={index}
            src={image.src}
            alt={image.alt}
            description={image.description}
            link={image.link}
          />
        ))}
      </div>
      <button onClick={handleClick}>
        {dark ? 'Light Mode' : 'Dark Mode'}
      </button>
    </div>
  );
}

export default function MainPage() {
  return <Homepage />;
}