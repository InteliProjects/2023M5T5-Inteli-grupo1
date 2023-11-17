import { blackA, mauve, green, violet } from '@radix-ui/colors';
/** @type {import('tailwindcss').Config} */
export default {
  // important: true,
  content: ["./src/**/*.tsx"],
  theme: {
    extend: {
      colors: {
        navbar: "#080654",
        ...blackA,
        ...mauve,
        ...green,
        ...violet,
      },
      fontFamily: {
        space: ["Space Grotesk"],
        inter: ["Inter"],
        sans: ['Roboto', 'sans-serif'],
        wans: ['Work Sans'],
        montserrat: ['Montserrat']
      },
      keyframes: {
        slideUpAndFade: {
          from: { opacity: 0, transform: 'translateY(2px)' },
          to: { opacity: 1, transform: 'translateY(0)' },
        },
        slideRightAndFade: {
          from: { opacity: 0, transform: 'translateX(-2px)' },
          to: { opacity: 1, transform: 'translateX(0)' },
        },
        slideDownAndFade: {
          from: { opacity: 0, transform: 'translateY(-2px)' },
          to: { opacity: 1, transform: 'translateY(0)' },
        },
        slideLeftAndFade: {
          from: { opacity: 0, transform: 'translateX(2px)' },
          to: { opacity: 1, transform: 'translateX(0)' },
        },
      },
      animation: {
        slideUpAndFade: 'slideUpAndFade 400ms cubic-bezier(0.16, 1, 0.3, 1)',
        slideRightAndFade: 'slideRightAndFade 400ms cubic-bezier(0.16, 1, 0.3, 1)',
        slideDownAndFade: 'slideDownAndFade 400ms cubic-bezier(0.16, 1, 0.3, 1)',
        slideLeftAndFade: 'slideLeftAndFade 400ms cubic-bezier(0.16, 1, 0.3, 1)',
      },
    },
  },
  plugins: [],
};