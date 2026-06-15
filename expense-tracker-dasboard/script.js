let transactions = [];

function addTransaction(){

    const desc =
    document.getElementById("desc").value;

    const amount =
    Number(document.getElementById("amount").value);

    transactions.push({
        desc,
        amount
    });

    render();

}

function render(){

    let total = 0;

    const list =
    document.getElementById("list");

    list.innerHTML = "";

    transactions.forEach(t => {

        total += t.amount;

        list.innerHTML +=
        `<li>${t.desc}: S/${t.amount}</li>`;

    });

    document.getElementById("total")
    .innerText = total;

}