import React from 'react'
import Square from '../square/Square'

class Board extends React.Component{

    constructor(props){
        super(props)
        this.state = {
            squares : Array(9).fill(null),
            xIsNext : true
        }
    }
    renderSquare(i){
        return <Square 
        value={this.state.squares[i]}
        onClick = {()=>this.handleClick(i)}
        />
    }
    
    handleClick(i){
        const squares = this.state.squares.slice()
        squares[i] = this.state.xIsNext?'X':'0'
        this.setState({
            squares:squares,
            xIsNext : !this.state.xIsNext
        })
    }
    render(){
        const winner = calculateWiner(this.state.squares)
        let status;
        if(winner){
            status = 'Winner : '+ winner
        }else{
            status = 'Next player is : '+(this.state.xIsNext?'X':'O')
        }
        return(
            <div>
            <div>{status}</div>

            <div className="board-row">
                {this.renderSquare(0)}
                {this.renderSquare(1)}
                {this.renderSquare(2)}
            </div>
            <div className="board-row">
                {this.renderSquare(3)}
                {this.renderSquare(4)}
                {this.renderSquare(5)}
            </div>
            <div className="board-row">
                {this.renderSquare(6)}
                {this.renderSquare(7)}
                {this.renderSquare(8)}
            </div>
        </div>
        )
    }
}

function calculateWiner(squares){
    const winner = [
        [0,1,2],
        [3,4,5],
        [6,7,8],
        [0,3,6],
        [1,4,7],
        [2,5,8],
        [0,4,8],
        [2,4,6]
    ]

    for(let i =0; i<winner.length; i++){
        const [a,b,c] = winner[i]
        if(squares[a] && squares[a]===squares[b] && squares[a]===squares[c]){
            return squares[a]
        }
    }
    return null
}
export default Board