import React from 'react';

class Comments extends React.Component {
  constructor(props) {
    super(props);

    this.state = {};
    this.state.textareaValue = '';
    this.state.comments = [];

    this.refresh = this.refresh.bind(this);
    this.textareaChanged = this.textareaChanged.bind(this);
    this.postComment = this.postComment.bind(this);

    this.refresh();
  }

  refresh() {
    fetch('/comment', {
      method: 'GET'
    }).then(response => response.json())
      .then(comments => {
        this.setState((prevState) => ({
          comments: comments
        }));
      });
  }

  textareaChanged(e) {
    const textarea = e.target;

    this.setState((prevState) => ({
      textareaValue: textarea.value
    }));
  }

  postComment() {
    fetch('/comment', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({text:this.state.textareaValue})
    }).then(response => {
      this.setState((prevState) => {
        return {
          ...prevState,
          textareaValue: '',
          comments: [
            ...prevState.comments,
            {
              author: this.props.username,
              text: prevState.textareaValue,
              createdAt: 'just now'
            }
          ]
        }
      });
    });
  }

  render() {
    let comments = [];
    for(const comment of this.state.comments) {
      comments.push(
        <div>
          <b><span>{comment.author}</span></b>
          <p>{comment.text}</p>
          <div><span>{comment.createdAt}</span></div>
          <hr />
        </div>
      );
    }

    return (
      <div>
        <div id='comments-section'>
          {comments}
        </div>
        <div id='post-comment-area'>
          <div>
            <textarea onChange={this.textareaChanged} value={this.state.textareaValue}/>
          </div>
          <button onClick={this.postComment}>Post</button>
          <button onClick={this.refresh}>Refresh</button>
        </div>
      </div>
    );
  }
}

export default Comments;
