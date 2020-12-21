import React from 'react';
import { withRouter } from 'react-router-dom';

class Info extends React.Component {
    render() {
        return <div>
            <p>Bonjour et bienvenue sur <b>Raspbetflix</b> ! Merci d'avoir téléchargé et installé notre image Raspbian préconfigurée pour vous proposer un service de gestions de médias sur votre réseau. Vous aller pouvoir dès maintenant brancher votre disque dur externe sur le Raspberry si ce n'est pas déjà fait.</p>
            <p>Vérifier que votre disque est bien monté dans la partie "mes disques".</p>
        </div>;
    }
}

export default withRouter(Info) 