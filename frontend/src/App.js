import React from 'react';
import './App.css';
import Comments from './Comments.js';
import getCookie from './cookie.js';

class App extends React.Component {
  constructor(props) {
    super(props);

    this.state = {}
    this.state.inputValue = '';
    this.state.username = getCookie('Auth');
    if (this.state.username) {
      this.state.authenticated = true;
    }

    this.inputChanged = this.inputChanged.bind(this);
    this.authenticate = this.authenticate.bind(this);
  }

  inputChanged(e) {
    const input = e.target;

    this.setState((prevState) => ({
      inputValue: input.value
    }));
  }

  authenticate() {
    fetch('authenticate', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      credentials: 'include',
      body: JSON.stringify({username: this.state.inputValue})
    }).then((response) => {
      this.setState((prevState) => ({
        authenticated: true,
        username: this.state.inputValue
      }));
    });
  }

  render() {
    let authenticateSection =
      <div>
        <input id='authorize-input' type='text' value={this.state.inputValue} onChange={this.inputChanged} />
        <button id='authorize-button' onClick={this.authenticate}>Authenticate</button>
      </div>;
    return (
      <div>
      {this.state.authenticated ? <Comments username={this.state.username}/> : authenticateSection}
      </div>
    );
  }
}

export default App;
