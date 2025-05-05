import React from 'react';
import './Campaign.css';

export default function Campaign() {
  return (
    <table class="campaign-table">
      <thead class="campaign-thead">
        <tr class="campaign-tr campaign-tr-header">
          <th class="campaign-th campaign-placement">Colocação</th>
          <th class="campaign-th campaign-event">Evento</th>
        </tr>
      </thead>
      <tbody class="campaign-tbody">
        <tr class="campaign-tr">
          <td class="campaign-td campaign-placement">1º</td>
          <td class="campaign-td campaign-event">ESL Pro League Season 12 North America</td>
        </tr>
        <tr class="campaign-tr">
          <td class="campaign-td campaign-placement">2º</td>
          <td class="campaign-td campaign-event">Esports Championship Series Season 7 Finals</td>
        </tr>
        <tr class="campaign-tr">
          <td class="campaign-td campaign-placement">3º–4º</td>
          <td class="campaign-td campaign-event">Intel Extreme Masters Rio 2024</td>
        </tr>
        <tr class="campaign-tr">
          <td class="campaign-td campaign-placement">3º–4º</td>
          <td class="campaign-td campaign-event">ESL Pro League Season 13</td>
        </tr>
        <tr class="campaign-tr">
          <td class="campaign-td campaign-placement">3º–4º</td>
          <td class="campaign-td campaign-event">Intel Extreme Masters Rio Major 2022</td>
        </tr>
        <tr class="campaign-tr">
          <td class="campaign-td campaign-placement">3º–4º</td>
          <td class="campaign-td campaign-event">ESL Pro League Season 15</td>
        </tr>
        <tr class="campaign-tr">
          <td class="campaign-td campaign-placement">3º–4º</td>
          <td class="campaign-td campaign-event">DreamHack Masters Dallas 2019</td>
        </tr>
        <tr class="campaign-tr">
          <td class="campaign-td campaign-placement">3º–4º</td>
          <td class="campaign-td campaign-event">ESL One: Cologne 2020 Online - North America</td>
        </tr>
        <tr class="campaign-tr">
          <td class="campaign-td campaign-placement">3º–4º</td>
          <td class="campaign-td campaign-event">Intel Extreme Masters Season XVII - Dallas</td>
        </tr>
      </tbody>
    </table>
  );
}
