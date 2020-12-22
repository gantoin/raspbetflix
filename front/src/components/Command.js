import React from 'react';
import { Table, Button } from 'react-bootstrap'
import { withRouter } from 'react-router-dom';

class Command extends React.Component {

    reboot() {
        fetch("http://" + window.location.hostname + ":8080/reboot", {
            headers: {
                "Content-Type": "application/json"
            }
        })
    }

    poweroff() {
        fetch("http://" + window.location.hostname + ":8080/poweroff", {
            headers: {
                "Content-Type": "application/json"
            }
        })
    }

    reset() {
        fetch("http://" + window.location.hostname + ":8080/reset", {
            headers: {
                "Content-Type": "application/json"
            }
        })
    }

    render() {
        return (<Table striped bordered hover className="Commands">
            <tbody>
                <tr>
                    <td className="CommandText">Redémarrer Raspbetflix</td>
                    <td className="Command"><Button onClick={() => this.reboot()} variant="primary">▶ Lancer</Button></td>
                </tr>
                <tr>
                    <td className="CommandText">Éteindre Raspbetflix <br /> (pour le démarrer de nouveau: débranchez et rebranchez-le)</td>
                    <td className="Command"><Button onClick={() => this.poweroff()} variant="primary">▶ Lancer</Button></td>
                </tr>
                <tr>
                    <td className="CommandText">Réinitialiser la configuration</td>
                    <td className="Command"><Button onClick={() => this.reset()} variant="primary">▶ Lancer</Button></td>
                </tr>
            </tbody>
        </Table >
        );
    }
}

export default withRouter(Command) 