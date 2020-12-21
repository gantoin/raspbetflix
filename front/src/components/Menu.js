import React from 'react';
import { Link, withRouter } from "react-router-dom";

class Menu extends React.Component {
    render() {
        return <div>
            <ul className="list-unstyled">
                <li>
                    → <a href="http://192.168.1.48:32400/web/index.html">Plex</a>
                </li>
                <li>
                    → <a href="http://192.168.1.48:9092/">Deluge</a>
                </li>
                <li>
                    → <Link to="/disks">Mes disques</Link>
                </li>
                <li>
                    → Commandes
                </li>
                <li>
                    → Explorateur de fichiers
                </li>
            </ul>
        </div>;
    }
}

export default withRouter(Menu); 