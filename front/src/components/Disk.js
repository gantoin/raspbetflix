import React from 'react';
import Table from 'react-bootstrap/Table'
import { withRouter } from 'react-router-dom';

class Disk extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            output: []
        };
    }

    componentDidMount() {
        fetch("http://" + window.location.hostname + ":8080/disk-free", {
            headers: {
                "Content-Type": "application/json"
            }
        })
            .then(res => res.json())
            .then(
                (result) => {
                    this.setState({
                        isLoaded: true,
                        output: result
                    });
                },
                (error) => {
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
            )
    }

    render() {
        const { error, isLoaded, output } = this.state;
        if (error) {
            return <div>⚠ Erreur : {error.message}</div>;
        } else if (!isLoaded) {
            return <div>Chargement…</div>;
        } else {
            return (<Table striped bordered hover className="Disks">
                <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Chemin</th>
                        <th>Espace occupé</th>
                    </tr>
                </thead>
                <tbody>

                    {output.map(item => (
                        <tr>
                            <td>{item.diskName}</td>
                            <td>{item.path}</td>
                            <td>{item.filling}%</td>
                        </tr>
                    ))}
                </tbody>
            </Table>
            );
        }
    }
}

export default withRouter(Disk) 