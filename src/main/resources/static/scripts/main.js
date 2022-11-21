const row = document.getElementsByClassName('row')[0];

function createColumn(name, location, position, link){
    const column = document.createElement('div');
    column.className = "column";

    column.append(createCard(name, location, position, link));

    return column;
}

function createCard(name, location, position, link){
    const card = document.createElement('div');
    card.className = "card";

    const jobName = document.createElement('div');
    jobName.className = "name";
    jobName.innerText = name;
    const jobLocation = document.createElement('div');
    jobLocation.className = "location";
    jobLocation.innerText = location;
    const jobPosition = document.createElement('div');
    jobPosition.className = "position";
    jobPosition.innerText = position;
    const expandButton = document.createElement('div');
    ///expandButton.className = "buttons";
    ///expandButton.innerHTML += '<button class="buttons" href="/addskills" role="button">More Details</button>';
    expandButton.innerHTML += '<button class="buttons" href=' + link + ' role="button">More Details</button>';

    card.append(jobName);
    card.append(jobLocation);
    card.append(jobPosition);
    card.append(expandButton);

    return card;
}

row.appendChild(createColumn("Company 1", "Location 1", "Position 1", "Link1"));
row.appendChild(createColumn("Company 2", "Location 2", "Position 2", "Link2"));
row.appendChild(createColumn("Company 3", "Location 3", "Position 3", "Link3"));
row.appendChild(createColumn("Company 4", "Location 4", "Position 4", "Link4"));
row.appendChild(createColumn("Company 5", "Location 5", "Position 5", "Link5"));
row.appendChild(createColumn("Company 6", "Location 6", "Position 6", "Link6"));