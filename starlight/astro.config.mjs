// @ts-check
import { defineConfig } from 'astro/config';
import starlight from '@astrojs/starlight';
import starlightThemeFlexoki from 'starlight-theme-flexoki';
//import starlight from '@astrojs/starlight'

// https://astro.build/config
export default defineConfig({
  site: 'https://evgenrudenko22.github.io',
  base: '/minejs',
  integrations: [
    starlight({
      plugins: [starlightThemeFlexoki({ accentColor: "blue" })],
      title: 'MineJS Docs',
      sidebar: [
        { slug: '' },
        { slug: 'faq', },
        {
          label: 'Events',
          items: [{ autogenerate: {directory: 'events' }}]
        },
        {
          label: 'Commands',
          items: [{ autogenerate: {directory: 'commands' }}]
        },
        {
          label: 'Utils',
          items: [{ autogenerate: {directory: 'utils' }}]
        },
        {
          label: 'Compatibility',
          items: [{ slug: 'compat/placeholder'}, {slug: 'compat/oraxen'}]
        },
      ],
      social: [
          { "icon": "github", "label": "GitHub", "href": "https://github.com/evgenrudenko22/minejs"}
      ]
    })
  ]
});