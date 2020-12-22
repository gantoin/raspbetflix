import React from 'react';
import { withRouter } from 'react-router-dom';
import { Link } from "react-router-dom";

class Info extends React.Component {
    render() {
        return <div>
            <p>Bonjour et bienvenue sur <b>Raspbetflix</b> ! Merci d'avoir téléchargé et installé notre image Raspbian préconfigurée pour vous proposer un service de gestions de médias sur votre réseau.
             Vous allez pouvoir dès maintenant brancher votre disque dur externe sur le Raspberry si ce n'est pas déjà fait.</p>
            <p>Vérifier que votre disque est bien monté dans la partie "<Link to="/disks">Mes disques</Link>". Il devrait apparaitre au chemin <i>/media/xx</i>.</p>
            <p>[ Si ce n'est pas le cas, votre disque doit surement être formaté à un format non reconnu par le Raspberry. Si vous tenez absolument à utiliser ce disque, essayez de le formater en choissisant un système de fichiers exFAT ou FAT32. ]</p>
            <p>Nous allons maintenant configurer votre serveur Plex, pour cela cliquez sur le lien "<a href={"http://" + window.location.hostname + ":32400/web/index.html"}>Plex</a>".</p>
            <p>Suivez la configuration du serveur proposé par Plex, en commençant par lui choisir un nom. Par exemple: "Plex Server" ou "Raspbetflix". Vous pouvez décocher <i>"M'autoriser à accéder à mes médias en dehors de ma maison".</i></p>
            <p>En cliquant sur 'Suivant' vous arriverez à la partie de gestion de vos bibliothèque. Vous pouvez ajouter une nouvelle bibliothèque de films (en français) par exemple en choissant un dossier de votre disque <i>/media/xx</i> ; par exemple <i>/media/usb0/films</i>.</p>
            <p>Vous pourrez répéter l'opération pour vos séries, musiques, etc.</p>
            <p>Un fois la configuration terminée, rendez-vous via l'interface Plex dans votre bibliothèque tout juste créee. Il vous sera demandé de vous connecter. Je vous conseille d'utiliser la connexion Google (facile et rapide).</p>
            <p>Un fois cela fait, allez dans votre bibliothèque et réclamez-la. Vous pourrez ensuite accéder à cette bibliothèque sur un client Plex (TV, smartphone, ...) en vous connectant au même compte Google et en étant bien sûr connecté à votre réseau (wifi, ethernet, etc.)</p>
            <p>Passons maintenant à la configuration de <a href={"http://" + window.location.hostname + ":9092/"}>Deluge</a>, le client Web pour télécharger des torrents.</p>
            <p>Le mot de passe par défaut est 'deluge' ; je vous recommande de le changer. Choisissez votre hôte local, qui devrait être déjà présent : <i>127.0.0.1</i> ou <i>localhost</i> et cliquez sur 'Connect'.</p>
            <p>Vous pouvez dès maintenant télécharger un fichier via n'importe quel torrent. N'oubliez pas de chnager la localication de destination pour le fichier. Prenez le dossier précédemment crée, par exemple <i>/media/usb0/films</i> s'il s'agit d'un film.</p>
            <p>La configuration VPN est en cours de développement.</p>
        </div>;
    }
}

export default withRouter(Info) 