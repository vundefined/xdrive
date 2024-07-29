export default function useAssets() {

  function getImageUrl(fileName) {
    return new URL(`../assets/images/${fileName}`, import.meta.url).href
  }

  return {getImageUrl}
}
