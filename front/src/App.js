import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import { Link, BrowserRouter as Router, Switch, Route } from "react-router-dom";

import Logo from './rasp.png';
import Disk from './components/Disk';
import Info from './components/Info';

import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';


class App extends React.Component {
  render() {
    return <Container>
      <Row className="HomePage">
        <div className="App">
          <img src={Logo} className="App-logo" alt="logo" />
        </div>
        <Router>
          <Col sm={4} className="Menu">
            <ul className="list-unstyled">
              <li>
                <Link to="/">Home</Link>
              </li>
              <li>
                <a href={"http://" + window.location.hostname + ":32400/web/index.html"}>Plex</a>
              </li>
              <li>
                <a href={"http://" + window.location.hostname + ":9092/"}>Deluge</a>
              </li>
              <li>
                <Link to="/disks">Mes disques</Link>
              </li>
              <li>
                → Commandes
              </li>
            </ul>
          </Col>
          <hr />
          <Col sm={8}>
            <Switch>
              <Route exact path="/">
                <Info />
              </Route>
              <Route path="/disks">
                <Disk />
              </Route>
            </Switch>
          </Col>
        </Router>
      </Row>
    </Container >
  }
}


export default App;
